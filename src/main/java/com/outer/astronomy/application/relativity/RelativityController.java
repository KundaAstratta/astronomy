package com.outer.astronomy.application.relativity;

import com.outer.astronomy.domain.entity.relativity.Relativity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.outer.astronomy.domain.utils.Constants.gravitationalConstant;

@RestController
public class RelativityController {

    @Operation(summary = "Calculate Einstein Tensor Space Curvature for flat spacetime", operationId = "EinsteinTensorWithFlatSpaceTime",
            description = "Flat spacetime (special relativity). " +
                    "The metric tensor of flat spacetime is given by the following diagonal matrix:                 " +
                    "                {-1, 0, 0, 0},\n" +
                    "                {0, 1, 0, 0},\n" +
                    "                {0, 0, 1, 0},\n" +
                    "                {0, 0, 0, 1}" +
                    "-1 corresponds to the time dimension and 1 to the spatial dimensions.",
            tags = {"relativity-controller"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Functional Error", content = @Content(mediaType ="application/json")),
            @ApiResponse(responseCode = "500", description = "Technical Error", content = @Content(mediaType ="application/json"))
    })
    @PostMapping("v1/EinsteinTensorWithFlatSpaceTime")
    public double[][] EinsteinTensorWithFlatSpaceTime() {
        double[][] metric = new double[][]{
                {-1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
        Relativity relativity = new Relativity(metric);
        return relativity.simulate();
    }

    @Operation(summary = "Calculate Einstein Tensor Space Curvature for Expanding Universe", operationId = "EinsteinTensorWithExpandingUniverse",
            description = "The Expanding Universe metric tensor in 4x4 form. " +
                    "                {-1 , 0, 0, 0},\n" +
                    "                {0, a * a, 0, 0},\n" +
                    "                {0, 0, (a * a) * (r * r), 0},\n" +
                    "                {0, 0, 0, (a * a) * (r * r) * Math.sin(theta) * Math.sin(theta)}" +
                    "where " +
                    "a is the scale factor of the universe, calculated with the formula : " +
                    "a = a0 * Math.pow((t / t0), 2.0 / 3.0) " +
                    "with :" +
                    "a0: The scale factor at the present epoch (current time) is a dimensionless parameter that represents the overall expansion of the universe. It is set to 1 in this code.\n" +
                    "(a0 = 1)\n" +
                    "t: Cosmic time is the time coordinate used in general relativity to describe the evolution of the universe. It is measured in seconds.\n" +
                    "(t= 1.1984048E10)\n" +
                    " t0: The age of the universe is the time elapsed since the Big Bang, the event that is thought to have marked the beginning of the universe. It is also measured in seconds.\n" +
                    "(t0 = 1) \n" +
                    "r represents the radial distance between an observer (who can be placed at any point in the universe) and any point in the universe" +
                    "(r= 3.0857E24)" +
                    "theta is the polar angle, which is defined in the framework of spherical coordinates" +
                    "(theta= 2.3)",
            tags = {"relativity-controller"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Functional Error", content = @Content(mediaType ="application/json")),
            @ApiResponse(responseCode = "500", description = "Technical Error", content = @Content(mediaType ="application/json"))
    })
    @PostMapping("v1/EinsteinTensorWithExpandingUniverse/{a0}/{t0}/{t}/{radial}/{theta}")
    public double[][] EinsteinTensorWithExpandingUniverse(@Parameter(description = "The scale factor at the present epoch (current time) is a dimensionless parameter that represents the overall expansion of the universe. It is set to 1 in this code.") @PathVariable double a0,
                                                          @Parameter(description = "The age of the universe is the time elapsed since the Big Bang, the event that is thought to have marked the beginning of the universe. It is also measured in seconds.") @PathVariable double t0,
                                                          @Parameter(description = "Cosmic time is the time coordinate used in general relativity to describe the evolution of the universe. It is measured in seconds.") @PathVariable double t,
                                                          @Parameter(description = "Radial distance between an observer (who can be placed at any point in the universe) and any point in the universe") @PathVariable double radial,
                                                          @Parameter(description = "Theta is the polar angle, which is defined in the framework of spherical coordinates") @PathVariable double theta) {
        double a = a0 * Math.pow((t / t0) , 2.0 / 3.0);
        double[][] metric = new double[][]{
                {-1 , 0, 0, 0},
                {0, a * a, 0, 0},
                {0, 0, (a * a) * (radial * radial), 0},
                {0, 0, 0, (a * a) * (radial * radial) * Math.sin(theta) * Math.sin(theta)}
        };
        Relativity relativity = new Relativity(metric);
        return relativity.simulate();
    }

    @Operation(summary = "Calculate Einstein Tensor Space Curvature for Friedmann-Lemaître-Robertson-Walker (FLRW)", operationId = "EinsteinTensorWithFLRW",
            description = "The FLRW spacetime metric tensor is a special case of an expanding universe, where the expansion is isotropic and homogeneous." +
                    "The FLRW metric tensor in 4x4 form. " +
                    "                {-1 , 0, 0, 0},\n" +
                    "                {0, a * a, 0, 0},\n" +
                    "                {0, 0, (a * a) * (r * r), 0},\n" +
                    "                {0, 0, 0, (a * a) * (r * r) * Math.sin(theta) * Math.sin(theta)}" +
                    "where " +
                    "a is the scale factor of the universe, calculated with the formula : " +
                    "a = a0 * Math.pow((t / t0), 2.0 / 3.0) " +
                    "with :" +
                    "a0: The scale factor at the present epoch (current time) is a dimensionless parameter that represents the overall expansion of the universe. It is set to 1 in this code.\n" +
                    "(a0 = 1)\n" +
                    "t: Cosmic time is the time coordinate used in general relativity to describe the evolution of the universe. It is measured in seconds.\n" +
                    "(t= 1.1984048E10)\n" +
                    " t0: The age of the universe is the time elapsed since the Big Bang, the event that is thought to have marked the beginning of the universe. It is also measured in seconds.\n" +
                    "(t0 = 1) \n" +
                    "k: This parameter represents the spatial curvature of the universe",
            tags = {"relativity-controller"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Functional Error", content = @Content(mediaType ="application/json")),
            @ApiResponse(responseCode = "500", description = "Technical Error", content = @Content(mediaType ="application/json"))
    })
    @PostMapping("v1/EinsteinTensorWithFLRW/{a0}/{t0}/{t}/{k}/{radial}/{theta}")
    public double[][] EinsteinTensorWithFLRW(@Parameter(description = "The scale factor at the present epoch (current time) is a dimensionless parameter that represents the overall expansion of the universe") @PathVariable double a0,
                                             @Parameter(description = "The age of the universe is the time elapsed since the Big Bang, the event that is thought to have marked the beginning of the universe.") @PathVariable double t0,
                                             @Parameter(description = "Cosmic time is the time coordinate used in general relativity to describe the evolution of the universe.") @PathVariable double t,
                                             @Parameter(description = "This parameter represents the spatial curvature of the universe") @PathVariable double k,
                                             @Parameter(description = "Radial is the radial distance") @PathVariable double radial,
                                             @Parameter(description = "Theta is the polar angle, which is defined in the framework of  ") @PathVariable double theta) {
        double a = a0 * Math.pow((t / t0) , 2.0 / 3.0);
        double[][] metric = new double[][]{
                {-1, 0, 0, 0},
                {0, a * a / (1 - k * radial * radial), 0, 0},
                {0, 0, (a * a) * (radial * radial), 0},
                {0, 0, 0, (a * a) * (radial * radial) * Math.sin(theta) * Math.sin(theta)}
        };
        Relativity relativity = new Relativity(metric);
        return relativity.simulate();
    }

    @Operation(summary = "Calculate Einstein Tensor Space Curvature for Schwarzschild Spacetime ", operationId = "EinsteinTensorWithSchwarzschildSpaceTime",
            description = "The Schwarzschild metric tensor in 4x4 form. " +
                    "                {-1 + 2 * G* M / r, 0, 0, 0},\n" +
                    "                {0, 1/(1 - 2 * G * M / r), 0, 0},\n" +
                    "                {0, 0, (r * r), 0},\n" +
                    "                {0, 0, 0, r * r * Math.sin(theta)* Math.sin(theta)}" +
                    "where " +
                    "   G is the gravitational constant\n" +
                    "   M (mass) is the mass of the black hole\n" +
                    "   r (radial) is the radial distance\n" +
                    "   theta is the colatitudinal angle",
            tags = {"relativity-controller"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Functional Error", content = @Content(mediaType ="application/json")),
            @ApiResponse(responseCode = "500", description = "Technical Error", content = @Content(mediaType ="application/json"))
    })
    @PostMapping("v1/EinsteinTensorWithSchwarzschildSpaceTime/{mass}/{radial}/{theta}")
    public double[][] EinsteinTensorWithSchwarzschildSpaceTime(@Parameter(description = "mass of the black hole") @PathVariable double mass,
                                                               @Parameter(description = "radial distance") @PathVariable double radial,
                                                               @Parameter(description = "theta is the colatitudinal angle ")@PathVariable double theta) {
        double[][] metric = new double[][]{
                {-1 + 2 * gravitationalConstant * mass / radial, 0, 0, 0},
                {0, 1/(1 - 2 * gravitationalConstant * mass / radial), 0, 0},
                {0, 0, (radial * radial), 0},
                {0, 0, 0, radial * radial * Math.sin(theta) * Math.sin(theta)}
        };
        Relativity relativity = new Relativity(metric);
        return relativity.simulate();
    }

}
