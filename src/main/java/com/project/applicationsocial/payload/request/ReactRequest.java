package com.project.applicationsocial.payload.request;

import com.project.applicationsocial.model.ENUM.ObjectTypeEnum;
import com.project.applicationsocial.model.ENUM.TypeReactEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReactRequest {
    private ObjectTypeEnum objectType;

    private UUID objectID;

    private TypeReactEnum type;
}
