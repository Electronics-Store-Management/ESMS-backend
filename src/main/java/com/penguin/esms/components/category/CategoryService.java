package com.penguin.esms.components.category;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepo categoryRepo;
}
