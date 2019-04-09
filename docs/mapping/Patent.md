Patent CERIF mapping
====================

https://openaire-guidelines-for-cris-managers.readthedocs.io/en/v1.1.1/cerif_xml_patent_entity.html

**Definitions**
`header = normdoc / part[name='record'] // header`
`knaw_long = normdoc / part[name='record'] // metadata / normalized / knaw_long`


Internal Identifier
-------------------
`knaw_long / uploadid`


Type
----
`patent (http://purl.org/coar/resource_type/c_15cd)`


Title
-----
`knaw_long // titleInfo / title`

Some `titleInfo` have a `xml:lang="en"` attribute, but this is always a duplicate of the title without `xml:lang`.
Use the `titleInfo` without `xml:lang` attribute.


VersionInfo
-----------
**not recorded**


RegistrationDate
----------------
`knaw_long // dateIssued / parsed`


ApprovalDate
------------
`knaw_long // dateAccepted / parsed`


CountryCode
-----------
**not recorded**


Issuer
------
**Narcis doesn't record the ORG identifiers of the organisations in `knaw_long`. If a `nameIdentifier` is given in `knaw_long`, we could do a lookup in VSOI and add the ORG identifier. Only then can we add the authors to this CERIF format.**

Narcis aggregates and doesn't have a ORG identifier for each organisation that is listed as issuer.
Also, Narcis records persons, while CERIF asks for organisations!


PatentNumber
------------
`knaw_long // patent_number`


Inventors
---------
**Narcis doesn't record the PRS identifiers of the authors in `knaw_long`. If a `nameIdentifier` is given in `knaw_long`, we could do a lookup in VSOI and add the PRS identifier. Only then can we add the authors to this CERIF format.**

Narcis aggregates and doesn't have a PRS identifier for each person that is listed as author.

`knaw_long // name` with `mcRoleTerm = 'inv'`


Holders
-------
**Narcis doesn't record the PRS identifiers of the authors in `knaw_long`. If a `nameIdentifier` is given in `knaw_long`, we could do a lookup in VSOI and add the PRS identifier. Only then can we add the authors to this CERIF format.**

Narcis aggregates and doesn't have a PRS identifier for each person that is listed as author.

`knaw_long // name` with `mcRoleTerm = 'pth'` # TODO bestaan deze?


Abstract
--------
`knaw_long // abstract`
<div style="page-break-after: always;"></div>


Subject
-------
**Probably not recorded, but also there is no clear documentation or examples on what kind of values/URIs are expected here.**


Keyword
-------
`knaw_long // subject / topic / topicValue`
*(might be that part of these belong in Subject, but as long as they don't have a URL, we can't do anything with it)*


OriginatesFrom
--------------
**This would require a link between these publications and the project(s) or funding(s) they originate from. Although some `grantAgreements` are provided in `knaw_long`, I'm not clear on whether we have a 'referential transparent identifier' pointing to that funding.**


Predecessor
-----------
**not recorded**


References
----------
**not recorded**
