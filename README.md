Narcis CERIF Experiments
========================

In the OpenAIRE Advance project, we implement the CERIF scheme for [Narcis](https://www.narcis.nl/).
As part of this we describe a mapping from the internal data representation to the CERIF format.


Running the examples
--------------------
To understand the data that is to be mapped, we wrote a number of queries on the data store of Narcis.

In order to run (some of) these, please follow the following instructions:
(_be sure to have access to the appropriate Narcis server; these instructions are written for MacOS_)

    # install
    brew cask install osxfuse
    brew install sshfs
     
    # mount
    sshfs <username>@<narcis-server>:<path-to-data-store> /Volumes/narcis-store -ovolname=Narcis
     
    # unmount
    umount /Volumes/tnarcis01-store


Related repositories
--------------------

* [guidelines-cris-managers](https://github.com/openaire/guidelines-cris-managers)
* [openaire-cris-validator](https://github.com/jdvorak001/openaire-cris-validator)
