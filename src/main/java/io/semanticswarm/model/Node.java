package io.semanticswarm.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Builder
@ToString
@EqualsAndHashCode
public class Node {
    private NodeContent nodeContent;
    private NodeId nodeId;
}
