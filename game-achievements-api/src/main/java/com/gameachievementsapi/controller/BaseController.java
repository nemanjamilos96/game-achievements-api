package com.gameachievementsapi.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path="/api/v1", produces= MediaType.APPLICATION_JSON_VALUE)
public abstract class BaseController {
}
