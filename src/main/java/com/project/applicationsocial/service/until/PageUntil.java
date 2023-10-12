package com.project.applicationsocial.service.until;

import com.project.applicationsocial.model.entity.Users;
import com.project.applicationsocial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class PageUntil {

    public static PageRequest parse(Integer page, Integer size, String field, String sort) {
        if (page == null || size == null) {
            return null;
        }
        if (field == null && sort == null) {
            return PageRequest.of(page - 1, size);
        }
        return PageRequest.of(page - 1, size, sort.equalsIgnoreCase("asc") ? Sort.by(field).ascending() : Sort.by(field).descending());
    }
}
