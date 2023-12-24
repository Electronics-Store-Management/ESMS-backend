package com.penguin.esms.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestCase {
    private Map<String, String> input = new HashMap<>();
    private Map<String, String> expected = new HashMap<>();
}
