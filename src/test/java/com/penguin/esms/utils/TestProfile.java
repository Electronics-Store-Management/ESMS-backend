package com.penguin.esms.utils;

import com.penguin.esms.components.authentication.responses.AuthenticationResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TestProfile {
    private List<TestCase> testCases;
    private AuthenticationResponse authenticationResponse;
}
