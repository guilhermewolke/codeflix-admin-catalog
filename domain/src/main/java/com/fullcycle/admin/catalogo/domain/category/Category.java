package com.fullcycle.admin.catalogo.domain.category;

import com.fullcycle.admin.catalogo.domain.AggregateRoot;
import com.fullcycle.admin.catalogo.domain.validation.ValidationHandler;
import com.fullcycle.admin.catalogo.domain.validation.handler.ThrowsValidationHandler;

import java.time.Instant;
import java.util.UUID;

public class Category extends AggregateRoot<CategoryID> {
    private String name;
    private String description;
    private boolean active;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private Category(final CategoryID id, final String name, final String description, final boolean active, final Instant createdAt, final Instant updatedAt, final Instant deletedAt) {
        super(id);
        this.name = name;
        this.description = description;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static Category newCategory(final String name, final String description, final boolean active) {
        final CategoryID id = CategoryID.unique();
        final Instant now = Instant.now();
        String theName = null;

        if (name != null) theName = name.trim();

        Instant deletedAt = null;
        if (!active) deletedAt = now;

        Category category = new Category(id, theName, description, active, now, now, deletedAt);

        category.validate(new ThrowsValidationHandler());

        return category;
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new CategoryValidator(this, handler).validate();
    }

    public Category deactivate() {
        this.active = false;
        final Instant now = Instant.now();
        this.deletedAt =  now;
        this.updatedAt =  now;

        return this;
    }

    public Category activate() {
        this.active = true;
        this.deletedAt = null;
        this.updatedAt = Instant.now();

        return this;
    }

    public Category changeName(String newName) {
        this.name = newName;
        this.updatedAt = Instant.now();

        this.validate(new ThrowsValidationHandler());

        return this;
    }

    public Category changeDescription(String description) {
        this.description = description;
        this.updatedAt = Instant.now();

        this.validate(new ThrowsValidationHandler());

        return this;
    }

    public CategoryID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }
}
