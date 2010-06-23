OSRA Plugin to allow copy/paste a chemical structure image into BKChem
molecular editor.

Created by Noel O'Boyle, updated for Linux by Igor Filippov

(1) Install Python 2.6 (or just use 2.4 or 2.5 if you have one of these
already)
(2) Install the Python Imaging Library 1.1.6 for your version of Python for
Windows version or PyGTK on Linux
(3) Download and extract BKChem-0.12.5.zip
(4) Drop convert_clipboard_image.py and convert_clipboard_image.xml into the
BKChem plugins folder
(5) Set the environment variable OSRA to the full path to osra.bat
(6) Find the Snapshot tool (it has a picture of a camera) in your version of
Adobe Reader. In version 9 it's under Tools/Select and Zoom/Snapshot Tool,
and you can add it to the toolbar under Views/Toolbars/More Tools.
OR
use Desktop Data Manager http://data-manager.sourceforge.net/ on Linux
use ClipMagic http://www.clipmagic.com/download.html on Windows
(7) Open a PDF of a paper or any image containing a molecular structure, and use the Snapshot tool to draw a box around a
molecule and hit CTRL+C to copy (if not done automatically).
(8) Start BKChem by double-clicking on bkchem.py (in the bkchem subfolder)
(9) Click "Plugins", "Paste and Convert Image"
