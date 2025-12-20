package org.example.agona11loggingtest.web;

import org.example.loggingstarter.exception.BadRequestException;
import org.example.loggingstarter.exception.InternalServerException;
import org.example.loggingstarter.exception.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/exceptions")
public class ExceptionTestController {

    @GetMapping("/not-found")
    public String throwNotFound() {
        throw new NotFoundException("Resource not found");
    }

    @GetMapping("/not-found/{id}")
    public String throwNotFoundWithCode(@PathVariable("id") String id) {
        throw new NotFoundException("RESOURCE_NOT_FOUND", String.format("Resource with id %s not found", id));
    }

    @GetMapping("/bad-request")
    public String throwBadRequest() {
        throw new BadRequestException("Invalid request parameters");
    }

    @GetMapping("/bad-request/{code}")
    public String throwBadRequestWithCode(@PathVariable("code") String code) {
        throw new BadRequestException("INVALID_PARAMETER", String.format("Invalid parameter: %s", code));
    }

    @GetMapping("/internal-server")
    public String throwInternalServer() {
        throw new InternalServerException("Internal server error occurred");
    }

    @GetMapping("/internal-server/{code}")
    public String throwInternalServerWithCode(@PathVariable("code") String code) {
        throw new InternalServerException("INTERNAL_ERROR", String.format("Internal error: %s", code));
    }

    @GetMapping("/generic")
    public String throwGeneric() {
        throw new RuntimeException("Generic runtime exception");
    }
}

