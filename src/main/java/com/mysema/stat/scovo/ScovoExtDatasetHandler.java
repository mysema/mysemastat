package com.mysema.stat.scovo;

import java.util.Map;

import com.mysema.rdfbean.model.RDF;
import com.mysema.rdfbean.model.RDFS;
import com.mysema.rdfbean.model.Repository;
import com.mysema.rdfbean.model.UID;
import com.mysema.stat.pcaxis.Dimension;
import com.mysema.stat.pcaxis.DimensionType;

/**
 * @author sasa
 *
 */
public class ScovoExtDatasetHandler extends ScovoDatasetHandler {

    public ScovoExtDatasetHandler(Repository repository, NamespaceHandler namespaceHandler, String baseURI) {
        super(repository, namespaceHandler, baseURI);
    }

    private UID getDimensionTypeUID(DimensionType type) {
        return new UID(dimensionBase, encodeID(type.getName()));
    }

    @Override
    protected void addDimensionType(DimensionType type, UID datasetsContext,
            UID datasetUID, UID domainContext, UID dimensionUID,
            Map<String, String> namespaces) {
        
        super.addDimensionType(type, datasetsContext, datasetUID, domainContext, dimensionUID, namespaces);
        
        // PROPERTY: subProperty of scv:dimension?
        UID dimensionProperty = getDimensionProperty(dimensionUID);
        add(dimensionProperty, RDF.type, RDF.Property, domainContext);
        add(dimensionProperty, RDFS.subPropertyOf, SCV.dimension, domainContext);
        add(dimensionProperty, RDFS.range, dimensionUID, domainContext);
    }

    @Override
    protected UID getDimensionProperty(Dimension dimension) {
        return getDimensionProperty(getDimensionTypeUID(dimension.getType()));
    }

    public static UID getDimensionProperty(UID dimensionType) {
        String ln = dimensionType.getLocalName();
        return new UID(dimensionType.getNamespace(), Character.toLowerCase(ln.charAt(0)) + ln.substring(1));
    }
    

}
