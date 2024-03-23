package com.outer.astronomy.application.blackhole;

import com.outer.astronomy.domain.entity.blackhole.Blackhole;
import com.outer.astronomy.domain.entity.blackhole.Features;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

@RestController
public class BlackholeController {

    @Operation(summary = "Is possible to cross the blackhole ?", operationId = "isTraversable",
               description = "Mass in kg  event horizon radius in m." +
                       "The throat radius of a blackhole is an important property that determines " +
                       "its geometry and traversability.",
               tags = {"blackhole-controller"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType ="application/json", schema = @Schema(implementation = Features.class))),
            @ApiResponse(responseCode = "400", description = "Functional Error", content = @Content(mediaType ="application/json")),
            @ApiResponse(responseCode = "500", description = "Technical Error", content = @Content(mediaType ="application/json"))
    })
    @PostMapping(value = "v1/isTraversable", produces = "application/json", consumes = "application/json")
    public Features isTraversable(@RequestBody Features features)  {
        Blackhole blackhole = new Blackhole(features);
        return new Features(features.mass(), features.radius(), blackhole.isTraversable(),features.eventHorizonArea(), features.entropy(), features.temperature());
    }

    @Operation(summary = "Schwarzschild radius", operationId = "getSchwarzschildRadius",
            description = "The Schwarzschild radius     (m) is another important radius that is related to the mass" +
                    " and the strength of its gravity." +
                    "The Schwarzschild radius is important because it determines the size of the event horizon," +
                    " beyond which nothing can escape.",
            tags = {"blackhole-controller"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType ="application/json", schema = @Schema(implementation = Features.class))),
            @ApiResponse(responseCode = "400", description = "Functional Error", content = @Content(mediaType ="application/json")),
            @ApiResponse(responseCode = "500", description = "Technical Error", content = @Content(mediaType ="application/json"))
    })
    @PostMapping("v1/getSchwarzschildRadius")
    public Features getSchwarzschildRadius(@RequestBody Features features) {
        Blackhole blackhole = new Blackhole(features);
        return new Features(features.mass(), blackhole.getSchwarzschildRadius(), features.isTraversable(), features.eventHorizonArea(), features.entropy(), features.temperature());
    }
    @Operation(summary = "Black holes are close enough ?", operationId = "isTooClose",
            description = "Check if black holes are close enough to have a wormhole." +
                    " Should not overlap",
            tags = {"blackhole-controller"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType ="application/json", schema = @Schema(implementation = Features.class))),
            @ApiResponse(responseCode = "400", description = "Functional Error", content = @Content(mediaType ="application/json")),
            @ApiResponse(responseCode = "500", description = "Technical Error", content = @Content(mediaType ="application/json"))
    })
    @PostMapping("v1/isTooClose/{distance}/{radius1}/{radius2}")
    public boolean isTooClose(@PathVariable double distance, @PathVariable double radius1, @PathVariable double radius2) {
        Blackhole blackhole = new Blackhole(null);
        return blackhole.isTooClose(distance, radius1,radius2);
    }

    @Operation(summary = "Time to cross wormhole", operationId = "timeToCross",
            description = "Traversal time (s). The isTooClose flag must be set to true." +
                    " Delta t = Traversal time (s). The isTooClose flag must be set to true." +
                    " Delta t = Integral from r1 to r2 of dr / (sqrt(1 - 2 * G * M / r))" +
                    " Delta t is the difference in proper time between two positions" +
                    " r1 and r2 are the initial and final radial distances" +
                    " Radial distance is used to describe the distance between an observer (or an object) and the center of a black hole.\n" +
                    " G is the gravitational constant" +
                    " M = m1 + m2",
            tags = {"blackhole-controller"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType ="application/json", schema = @Schema(implementation = Features.class))),
            @ApiResponse(responseCode = "400", description = "Functional Error", content = @Content(mediaType ="application/json")),
            @ApiResponse(responseCode = "500", description = "Technical Error", content = @Content(mediaType ="application/json"))
    })
    @PostMapping("v1/timeToCrossApproachCalculus/{r1}/{r2}/{mass1}/{mass2}/{radius1}/{radius2}")
    public double timeToCrossApproachCalculus(@PathVariable double r1,@PathVariable double r2, @PathVariable double mass1, @PathVariable double mass2 , @PathVariable double radius1, @PathVariable double radius2) {
        Blackhole blackhole = new Blackhole(null);
        if (blackhole.isTooClose(Math.abs(r2-r1),radius1,radius2)) {
            throw new IllegalArgumentException("Too close");
        } else {
            return blackhole.timeToCrossApproachCalculus(r1,r2,mass1,mass2);
        }
    }
    @Operation(summary = "Event horizon", operationId = "eventHorizonArea",
            description = "Event horizon area of a black hole (m^2).",
            tags = {"blackhole-controller"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType ="application/json", schema = @Schema(implementation = Features.class))),
            @ApiResponse(responseCode = "400", description = "Functional Error", content = @Content(mediaType ="application/json")),
            @ApiResponse(responseCode = "500", description = "Technical Error", content = @Content(mediaType ="application/json"))
    })
    @PostMapping("v1/eventHorizonArea")
    public Features eventHorizonArea(@RequestBody Features features) {
        Blackhole blackhole = new Blackhole(features);
        return new Features(features.mass(), features.radius(), features.isTraversable(), blackhole.eventHorizonArea(), features.entropy(), features.temperature());
    }

    @Operation(summary = "Entropy", operationId = "entropy",
            description = "Entropy J/K.",
            tags = {"blackhole-controller"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType ="application/json", schema = @Schema(implementation = Features.class))),
            @ApiResponse(responseCode = "400", description = "Functional Error", content = @Content(mediaType ="application/json")),
            @ApiResponse(responseCode = "500", description = "Technical Error", content = @Content(mediaType ="application/json"))
    })
    @PostMapping("v1/entropy")
    public Features entropy(@RequestBody Features features) {
        Blackhole blackhole = new Blackhole(features);
        return new Features(features.mass(), features.radius(), features.isTraversable(), features.eventHorizonArea(), blackhole.entropy(features.eventHorizonArea()), features.temperature());
    }

    @Operation(summary = "Temperature", operationId = "temperature",
            description = "Temparature of blackhole in K.",
            tags = {"blackhole-controller"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType ="application/json", schema = @Schema(implementation = Features.class))),
            @ApiResponse(responseCode = "400", description = "Functional Error", content = @Content(mediaType ="application/json")),
            @ApiResponse(responseCode = "500", description = "Technical Error", content = @Content(mediaType ="application/json"))
    })
    @PostMapping("v1/temparature")
    public Features temperature(@RequestBody Features features) {
        Blackhole blackhole = new Blackhole(features);
        return new Features(features.mass(), features.radius(), features.isTraversable(),  features.eventHorizonArea(), features.entropy(),blackhole.temperature());
    }

}
