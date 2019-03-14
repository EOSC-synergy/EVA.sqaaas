Product CERIF mapping
=====================

https://openaire-guidelines-for-cris-managers.readthedocs.io/en/v1.1.1/cerif_xml_product_entity.html

**Definitions**
`header = normdoc / part[name='record'] // header`
`knaw_long = normdoc / part[name='record'] // metadata / normalized / knaw_long`


Internal Identifier
-------------------
`header / identifier`


Type
----
`knaw_long // genre`

For now, always 'dataset (http://purl.org/coar/resource_type/c_ddb1)'
Once subtype 'software' is provided, map those to 'software (http://purl.org/coar/resource_type/c_5ce6)'


Language
--------
`knaw_long // language`


Name
----
`knaw_long // titleInfo / title`

Some `titleInfo` have a `xml:lang="en"` attribute, but this is always a duplicate of the title without `xml:lang`.
Use the `titleInfo` without `xml:lang` attribute.


VersionInfo
-----------
**not recorded**
<div style="page-break-after: always;"></div>


ARK
----
**not recorded**


DOI
----
`knaw_long // publication_identifier[@type='doi']`


Handle
------
`knaw_long // publication_identifier[@type='hdl']`


URL
----
`knaw_long // publication_identifier[@type='purl']`


URN
----
`knaw_long // publication_identifier[@type='nbn']`


Creators
--------
**Narcis doesn't record the PRS identifiers of the authors in `knaw_long`. If a `nameIdentifier` is given in `knaw_long`, we could do a lookup in VSOI and add the PRS identifier. Only then can we add the authors to this CERIF format.**

Narcis aggregates and doesn't have a PRS identifier for each person that is listed as author.


Publishers
----------
**No identifier available to link with.**
<div style="page-break-after: always;"></div>


License
-------
`knaw_long // rightsDescription`

* *Not sure if this element can be added though. (1) I haven't seen it in use yet in the `knaw` store and (2) I'm not sure if we can offer both a `value` and a `scheme` for this.*
* *Can we map our values to the expected scheme? https://www.openaire.eu/cerif-profile/vocab/LicenseTypes#*


Description
-----------
`knaw_long // abstract`


Subject
-------
**Probably not recorded, but also there is no clear documentation or examples on what kind of values/URIs are expected here.**


Keyword
-------
`knaw_long // subject / topic / topicValue`
*(might be that part of these belong in Subject, but as long as they don't have a URL, we can't do anything with it)*


PartOf
------
**not recorded**


Originates
----------
**This would require a link between these publications and the project(s) or funding(s) they originate from. Although some `grantAgreements` are provided in `knaw_long`, I'm not clear on whether we have a 'referential transparent identifier' pointing to that funding.**


GeneratedBy
-----------
**Narcis doesn't record any `Equipment` information that would be required for this entry.**


PresentedAt
-----------
**Narcis doesn't record any `Event` information that would be required for this entry.**


Coverage
--------
**Narcis doesn't record any `Event` information that would be required for this entry.**


References
----------
**Not recorded**


ns4:Access
----------
`knaw_long / accessRights`

**mapping:**

`knaw_long`        | *CERIF*
------------------ | -------
*openAccess*       | open access (http://purl.org/coar/access_right/c_abf2)
*embargoedAccess*  | embargoed access (http://purl.org/coar/access_right/c_f1cf)
*restrictedAccess* | restricted access (http://purl.org/coar/access_right/c_16ec)
*closedAccess*     | metadata only access (http://purl.org/coar/access_right/c_14cb)
