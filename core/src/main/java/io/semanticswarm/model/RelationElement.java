package io.semanticswarm.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Builder
@ToString
@EqualsAndHashCode
public class RelationElement {
    private Node object;
    private Node subject;
    private Node attribute;
}
