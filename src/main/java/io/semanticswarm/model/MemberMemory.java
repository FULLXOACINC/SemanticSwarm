package io.semanticswarm.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;
@Builder
@ToString
@EqualsAndHashCode
public class MemberMemory {
    private List<Relation> relations;
}
