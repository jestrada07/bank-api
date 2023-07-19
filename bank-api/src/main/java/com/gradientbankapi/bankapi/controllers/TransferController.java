package com.gradientbankapi.bankapi.controllers;

import com.gradientbankapi.bankapi.code_response.CodeFactorWithoutData;
import com.gradientbankapi.bankapi.code_response.CodeMessageFactor;
import com.gradientbankapi.bankapi.models.Transfer;
import com.gradientbankapi.bankapi.services.TransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class TransferController {

    private static final Logger logger = LoggerFactory.getLogger(TransferController.class);

    @Autowired
    private TransferService transferService;


    @PostMapping("/accounts/{accountIdOne}/accounts/{accountIdTwo}")
    public ResponseEntity<Object> createATransfer(@PathVariable Long accountIdOne,@PathVariable Long accountIdTwo, @RequestBody Transfer transfer) {
        try {
            CodeMessageFactor success = new CodeMessageFactor(201, "Transfer successfully completed!",
                    transferService.transferringAmount(accountIdOne,accountIdTwo, transfer));
            logger.info("Transfer successfully completed!");
            return new ResponseEntity<>(success, HttpStatus.CREATED);
        } catch (Exception e) {
            CodeFactorWithoutData error = new CodeFactorWithoutData(400, "Error! Cannot complete transfer!");
            logger.info("Error! Cannot complete transfer!");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }
}
