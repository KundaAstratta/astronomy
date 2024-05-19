package com.outer.astronomy.application.blackhole;

import com.outer.astronomy.domain.entity.blackhole.Blackhole;
import com.outer.astronomy.domain.entity.blackhole.Chandrasekhar;
import com.outer.astronomy.domain.entity.blackhole.FeaturesBlackhole;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType ="application/json", schema = @Schema(implementation = FeaturesBlackhole.class))),
            @ApiResponse(responseCode = "400", description = "Functional Error", content = @Content(mediaType ="application/json")),
            @ApiResponse(responseCode = "500", description = "Technical Error", content = @Content(mediaType ="application/json"))
    })
    @PostMapping(value = "v1/isTraversable", produces = "application/json", consumes = "application/json")
    public FeaturesBlackhole isTraversable(@RequestBody FeaturesBlackhole featuresBlackhole)  {
        Blackhole blackhole = new Blackhole(featuresBlackhole);
        return new FeaturesBlackhole(featuresBlackhole.mass(), featuresBlackhole.radius(), blackhole.isTraversable(), featuresBlackhole.eventHorizonArea(), featuresBlackhole.entropy(), featuresBlackhole.temperature());
    }

    @Operation(summary = "Schwarzschild radius", operationId = "getSchwarzschildRadius",
            description = "The Schwarzschild radius     (m) is another important radius that is related to the mass" +
                    " and the strength of its gravity." +
                    "The Schwarzschild radius is important because it determines the size of the event horizon," +
                    " beyond which nothing can escape.",
            tags = {"blackhole-controller"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType ="application/json", schema = @Schema(implementation = FeaturesBlackhole.class))),
            @ApiResponse(responseCode = "400", description = "Functional Error", content = @Content(mediaType ="application/json")),
            @ApiResponse(responseCode = "500", description = "Technical Error", content = @Content(mediaType ="application/json"))
    })
    @PostMapping("v1/getSchwarzschildRadius")
    public FeaturesBlackhole getSchwarzschildRadius(@RequestBody FeaturesBlackhole featuresBlackhole) {
        Blackhole blackhole = new Blackhole(featuresBlackhole);
        return new FeaturesBlackhole(featuresBlackhole.mass(), blackhole.getSchwarzschildRadius(), featuresBlackhole.isTraversable(), featuresBlackhole.eventHorizonArea(), featuresBlackhole.entropy(), featuresBlackhole.temperature());
    }
    @Operation(summary = "Black holes are close enough ?", operationId = "isTooClose",
            description = "Check if black holes are close enough to have a wormhole." +
                    " Should not overlap",
            tags = {"blackhole-controller"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType ="application/json", schema = @Schema(implementation = FeaturesBlackhole.class))),
            @ApiResponse(responseCode = "400", description = "Functional Error", content = @Content(mediaType ="application/json")),
            @ApiResponse(responseCode = "500", description = "Technical Error", content = @Content(mediaType ="application/json"))
    })
    @PostMapping("v1/isTooClose/{distance}/{radius1}/{radius2}")
    public boolean isTooClose(@Parameter(description = "distance of two blackholes") @PathVariable double distance,
                              @Parameter(description = "radius of the first blackhole")  @PathVariable double radius1,
                              @Parameter(description = "radius of the second blackhole") @PathVariable double radius2) {
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
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType ="application/json", schema = @Schema(implementation = FeaturesBlackhole.class))),
            @ApiResponse(responseCode = "400", description = "Functional Error", content = @Content(mediaType ="application/json")),
            @ApiResponse(responseCode = "500", description = "Technical Error", content = @Content(mediaType ="application/json"))
    })
    @PostMapping("v1/timeToCrossApproachCalculus/{r1}/{r2}/{mass1}/{mass2}/{radius1}/{radius2}")
    public double timeToCrossApproachCalculus(@Parameter(description = "initial radial distance (first blackhole)") @PathVariable double r1,
                                              @Parameter(description = "final radial distance (second blackhole") @PathVariable double r2,
                                              @Parameter(description = "mass of the first blackhole") @PathVariable double mass1,
                                              @Parameter(description = "mass of the second blackhole") @PathVariable double mass2 ,
                                              @Parameter(description = "radius of the first blackhole") @PathVariable double radius1,
                                              @Parameter(description = "radius of the second blackhole") @PathVariable double radius2) {
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
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType ="application/json", schema = @Schema(implementation = FeaturesBlackhole.class))),
            @ApiResponse(responseCode = "400", description = "Functional Error", content = @Content(mediaType ="application/json")),
            @ApiResponse(responseCode = "500", description = "Technical Error", content = @Content(mediaType ="application/json"))
    })
    @PostMapping("v1/eventHorizonArea")
    public FeaturesBlackhole eventHorizonArea(@RequestBody FeaturesBlackhole featuresBlackhole) {
        Blackhole blackhole = new Blackhole(featuresBlackhole);
        return new FeaturesBlackhole(featuresBlackhole.mass(), featuresBlackhole.radius(), featuresBlackhole.isTraversable(), blackhole.eventHorizonArea(), featuresBlackhole.entropy(), featuresBlackhole.temperature());
    }

    @Operation(summary = "Entropy", operationId = "entropy",
            description = "Entropy J/K.",
            tags = {"blackhole-controller"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType ="application/json", schema = @Schema(implementation = FeaturesBlackhole.class))),
            @ApiResponse(responseCode = "400", description = "Functional Error", content = @Content(mediaType ="application/json")),
            @ApiResponse(responseCode = "500", description = "Technical Error", content = @Content(mediaType ="application/json"))
    })
    @PostMapping("v1/entropy")
    public FeaturesBlackhole entropy(@RequestBody FeaturesBlackhole featuresBlackhole) {
        Blackhole blackhole = new Blackhole(featuresBlackhole);
        return new FeaturesBlackhole(featuresBlackhole.mass(), featuresBlackhole.radius(), featuresBlackhole.isTraversable(), featuresBlackhole.eventHorizonArea(), blackhole.entropy(featuresBlackhole.eventHorizonArea()), featuresBlackhole.temperature());
    }

    @Operation(summary = "Temperature", operationId = "temperature",
            description = "Temparature of blackhole in K.",
            tags = {"blackhole-controller"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType ="application/json", schema = @Schema(implementation = FeaturesBlackhole.class))),
            @ApiResponse(responseCode = "400", description = "Functional Error", content = @Content(mediaType ="application/json")),
            @ApiResponse(responseCode = "500", description = "Technical Error", content = @Content(mediaType ="application/json"))
    })
    @PostMapping("v1/temparature")
    public FeaturesBlackhole temperature(@RequestBody FeaturesBlackhole featuresBlackhole) {
        Blackhole blackhole = new Blackhole(featuresBlackhole);
        return new FeaturesBlackhole(featuresBlackhole.mass(), featuresBlackhole.radius(), featuresBlackhole.isTraversable(),  featuresBlackhole.eventHorizonArea(), featuresBlackhole.entropy(),blackhole.temperature());
    }



    @Operation(summary = "Chandrasekhar limit equation", operationId = "chandrasekharLimit",
            description = "Chandrasekhar limit equation calculates the maximum mass M(limit) " +
                    "of a stable white dwarf star, above which it would collapse under its own gravity, leading to a neutron star or black hole. " +
                    "μe is the mean molecular weight per electron and Ms is the solar mass." +
                    "M(limit) = 1,44 x (2/μe)^2 x Ms." +
                    "fractions (molar) -> (70% d'hélium):0.7 - (20% de carbone):0.2 -  (10% d'oxygène):0.1." +
                    "atomic Masses -> He = 4, C=12, O=16" +
                    "atomic Numbers -> He = 2, C=6, O=8",
            tags = {"blackhole-controller"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType ="application/json")),
            @ApiResponse(responseCode = "400", description = "Functional Error", content = @Content(mediaType ="application/json")),
            @ApiResponse(responseCode = "500", description = "Technical Error", content = @Content(mediaType ="application/json"))
    })
    @PostMapping("v1/chandrasekharLimit/{fractions}/{atomicNumbers}/{atomicMasses}")
    public double chandrasekharLimit(@Parameter(description = "fractions in % of the each atom") @PathVariable double[] fractions,
                                     @Parameter(description = "atomic number of each atom")  @PathVariable int[] atomicNumbers,
                                     @Parameter(description = "atomic mass of each atom") @PathVariable double[] atomicMasses) {
        Chandrasekhar chandrasekhar = new Chandrasekhar(fractions,atomicNumbers,atomicMasses);
        return chandrasekhar.calculateChandrasekharLimit();
    }

}
