package com.sky.assignment.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Abstract Data Transfer Object root class.
 * 
 * @author switalski
 */
public abstract class AbstractDto implements Serializable {
	
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, false);
	}
	
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, false);
	};
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	private static final long serialVersionUID = 1L;

}
