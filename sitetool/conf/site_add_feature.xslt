<?xml version="1.0" encoding="Shift_JIS"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

	<xsl:output method="xml" encoding="UTF-8" indent="yes" />

	<xsl:param name="feature.name"></xsl:param>
	<xsl:param name="feature.id"></xsl:param>
	<xsl:param name="feature.version"></xsl:param>
	<xsl:param name="feature.version.main"></xsl:param>
	<xsl:param name="feature.version.build"></xsl:param>

	<xsl:variable name="mFeatures" select="/site[1]/feature"/>
	<xsl:variable name="mCategoryDefs" select="/site[1]/category-def"/>

	<xsl:template match="*">
		<xsl:copy>
			<xsl:for-each select="@*">
				<xsl:copy />
			</xsl:for-each>
			<xsl:apply-templates />
		</xsl:copy>
	</xsl:template>

	<xsl:template match="description">
		<xsl:copy>
			<xsl:for-each select="@*">
				<xsl:copy />
			</xsl:for-each>
			<xsl:copy-of select="text()" />
		</xsl:copy>
		<xsl:variable name="category.name" select="concat('OpenRTP ', $feature.version.main)" />
		<xsl:if test="not($mFeatures[@id=$feature.id and @version=$feature.version])">
			<feature>
				<xsl:attribute name="url">
					<xsl:value-of select="concat('features/', $feature.name, '.jar')" />
				</xsl:attribute>
				<xsl:attribute name="id">
					<xsl:value-of select="$feature.id" />
				</xsl:attribute>
				<xsl:attribute name="version">
					<xsl:value-of select="$feature.version" />
				</xsl:attribute>
				<category>
					<xsl:attribute name="name">
						<xsl:value-of select="$category.name" />
					</xsl:attribute>
				</category>
			</feature>
		</xsl:if>
		<xsl:if test="not($mCategoryDefs[@name=$category.name])">
			<category-def>
				<xsl:attribute name="name">
					<xsl:value-of select="$category.name" />
				</xsl:attribute>
				<xsl:attribute name="label">
					<xsl:value-of select="$category.name" />
				</xsl:attribute>
			</category-def>
		</xsl:if>
	</xsl:template>

</xsl:stylesheet>
