/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.44).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package global.govstack.payment.bb.mock.controller;

import global.govstack.payment.bb.mock.dto.BulkpaymentBody;
import global.govstack.payment.bb.mock.dto.InlineResponse200;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CookieValue;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
import java.util.Map;

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-06-02T13:05:58.136949+03:00[Europe/Sofia]")
@Validated
@Api(value = "bulk-payment", description = "the bulk-payment API")
public interface BulkPaymentApi {

    @ApiOperation(value = "Bulk payment processing", nickname = "bulkPaymentPost", notes = "", response = InlineResponse200.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful operation", response = InlineResponse200.class),
        @ApiResponse(code = 400, message = "Bad request") })
    @RequestMapping(value = "/bulk-payment",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<InlineResponse200> bulkPaymentPost(@ApiParam(value = "", required=true ) @Valid @RequestBody BulkpaymentBody body);

}

