A patch to allow using OSRA to import or copy/paste molecular structure
images into Molsketch editor (http://molsketch.sourceforge.net/).
Created by Igor Filippov, 2009.

To install - 
download molsketch-0.1.1deuterium and follow the steps:
tar -xzf molsketch-0.1.1deuterium.tar.gz
cd molsketch-0.1.1deuterium/src
patch <../../molsketch-osra.patch
cd ..
mkdir build
cd build
cmake ../
make
make install

Set up environment variable OSRA (or make sure osra is in the PATH) and
start molsketch. You can use File->Import functionality or Edit->Convert
Image To Mol to use OSRA.

For  copy/paste functionality you can either
- use Desktop Data Manager http://data-manager.sourceforge.net/ on Linux
- use ClipMagic http://www.clipmagic.com/download.html on Windows
- for PDF files use the Snapshot tool (it has a picture of a camera) in your version of
Adobe Reader. In version 9 it's under Tools/Select and Zoom/Snapshot Tool,
and you can add it to the toolbar under Views/Toolbars/More Tools.
