/*
 * Copyright (c) 2022 Red Hat, Inc.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Red Hat, Inc. - initial API and implementation
 */
package org.acme;

import java.net.URI;
import java.util.List;

import io.quarkus.panache.common.Sort;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/food")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FoodResource {

    @GET
    public List<Food> list() {
        return Food.listAll(Sort.by("id"));
    }

    @GET
    @Path("/{id}")
    public Food getById(@PathParam("id") Long id) {
        return Food.findById(id);
    }

    @POST
    @Transactional
    public Response create(Food food) {
        food.persist();
        if (food.isPersistent()) {
            return Response.created(URI.create("/food/" + food.id)).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @GET
    @Path("search/{name}")
    public Food getByName(@PathParam("name") String name) {
        return Food.find("name", name).firstResult();
    }

    @GET
    @Path("restaurant/{restaurantName}")
    public List<Food> listByRestaurant(@PathParam("restaurantName") String restaurantName) {
        return Food.find("restaurantName", Sort.by("name"), restaurantName).list();
    }
}
