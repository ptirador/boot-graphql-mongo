package com.ptirador.graphql.controller;

import com.ptirador.graphql.utils.GraphQLUtility;
import graphql.ExecutionResult;
import graphql.GraphQL;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.logging.Logger;

@RestController
public class MainController {

    private static final Logger LOG = Logger.getLogger(MainController.class.getName());

    private final GraphQLUtility graphQLUtility;
    private GraphQL graphQL;

    public MainController(final GraphQLUtility graphQLUtility) {
        this.graphQLUtility = graphQLUtility;
    }

    @PostConstruct
    private void init() throws IOException {
        graphQL = this.graphQLUtility.createGraphQLObject();
    }

    @PostMapping("/query")
    public ResponseEntity query(@RequestBody String query) {
        ExecutionResult result = graphQL.execute(query);
        LOG.info("errors: " + result.getErrors());
        return ResponseEntity.ok(result.getData());
    }
}