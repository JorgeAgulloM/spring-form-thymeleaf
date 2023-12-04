package com.softyorch.cursospring.form.app.editors;

import java.beans.PropertyEditorSupport;

public class NameUppercaseEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        setValue(text.toUpperCase().trim());
    }
}
