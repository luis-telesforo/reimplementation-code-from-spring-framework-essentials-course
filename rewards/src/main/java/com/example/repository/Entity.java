package com.example.repository;

/**
 * A base class for all entities that use an internal {@link Long} id.
 *
 * @param id the id.
 */
public record Entity(Long id) {
}
