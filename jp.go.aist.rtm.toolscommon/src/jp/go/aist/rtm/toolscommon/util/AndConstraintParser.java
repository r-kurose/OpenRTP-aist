package jp.go.aist.rtm.toolscommon.util;

import org.openrtp.namespaces.rtc.version02.And;
import org.openrtp.namespaces.rtc.version02.ConstraintType;
import org.openrtp.namespaces.rtc.version02.ConstraintUnitType;
import org.openrtp.namespaces.rtc.version02.PropertyIsBetween;
import org.openrtp.namespaces.rtc.version02.PropertyIsGreaterThan;
import org.openrtp.namespaces.rtc.version02.PropertyIsGreaterThanOrEqualTo;
import org.openrtp.namespaces.rtc.version02.PropertyIsLessThan;
import org.openrtp.namespaces.rtc.version02.PropertyIsLessThanOrEqualTo;

/**
 * コンフィグデータの制約条件内での、 And 条件を解釈するクラス
 *
 */
public class AndConstraintParser {
	private String greaterThan;
	private String greaterThanEqual;
	private String lessThan;
	private String lessThanEqual;
	
	public String parse(And and) {
		for (ConstraintType constraint : and.getConstraint()) {
			ConstraintUnitType constraintUnitType = constraint.getConstraintUnitType();
			if (constraintUnitType == null) continue;
			parseGreaterThan(constraintUnitType.getPropertyIsGreaterThan());
			parseGreaterThanEqual(constraintUnitType.getPropertyIsGreaterThanOrEqualTo());
			parseLessThan(constraintUnitType.getPropertyIsLessThan());
			parseLessThanEqual(constraintUnitType.getPropertyIsLessThanOrEqualTo());
		}
		return buildResult();
	}

	private String buildResult() {
		StringBuffer buffer = new StringBuffer();
		if (greaterThan != null) {
			buffer.append(greaterThan).append("<x");
		} else if (greaterThanEqual != null ) {
			buffer.append(greaterThanEqual).append("<=x");
		}
		if (lessThan != null) {
			if (buffer.length() == 0) buffer.append("x");
			buffer.append("<").append(lessThan);
		} else if (lessThanEqual != null){
			if (buffer.length() == 0) buffer.append("x");
			buffer.append("<=").append(lessThanEqual);			
		}
		return buffer.toString();
	}

	public String parse(PropertyIsBetween propertyIsBetween) {
		parseGreaterThanEqual(propertyIsBetween.getLowerBoundary());
		parseLessThanEqual(propertyIsBetween.getUpperBoundary());
		return buildResult();
	}

	private void parseGreaterThan(PropertyIsGreaterThan propertyIsGreaterThan) {
		if (propertyIsGreaterThan == null) return;
		if (greaterThan != null) return;
		greaterThan = propertyIsGreaterThan.getLiteral();
	}

	private void parseGreaterThanEqual(PropertyIsGreaterThanOrEqualTo propertyIsGreaterThanOrEqualTo) {
		if (propertyIsGreaterThanOrEqualTo == null) return;
		parseGreaterThanEqual(propertyIsGreaterThanOrEqualTo.getLiteral());
	}

	private void parseGreaterThanEqual(String lowerBoundary) {
		if (greaterThanEqual != null) return;
		greaterThanEqual = lowerBoundary;
	}

	private void parseLessThan(PropertyIsLessThan propertyIsLessThan) {
		if (propertyIsLessThan == null) return;
		if (lessThan != null) return;
		lessThan = propertyIsLessThan.getLiteral();
	}

	private void parseLessThanEqual(PropertyIsLessThanOrEqualTo propertyIsLessThanOrEqualTo) {
		if (propertyIsLessThanOrEqualTo == null) return;
		parseLessThanEqual(propertyIsLessThanOrEqualTo.getLiteral());
	}

	private void parseLessThanEqual(String upperBoundary) {
		if (lessThanEqual != null) return;
		lessThanEqual = upperBoundary;
	}

}
