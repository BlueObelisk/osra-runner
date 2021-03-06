                        OSRA: Optical Structure Recognition

							Igor Filippov, 2007-2010
							igorf@helix.nih.gov


Description:

OSRA is a utility designed to convert graphical representations of
chemical structures, as they appear in journal articles, patent documents,
textbooks, trade magazines etc., into SMILES (Simplified Molecular
Input Line Entry Specification - see http://en.wikipedia.org/wiki/SMILES ) - 
a computer recognizable molecular structure format.  OSRA can read a document 
in any of the over 90 graphical formats parseable by ImageMagick - including 
GIF, JPEG, PNG, TIFF, PDF, PS etc., and generate the SMILES representation of
the molecular structure images encountered within that document.

Note that any software designed for optical recognition is unlikely to
be perfect, and the output produced might, and probably will, contain
errors, so a curation by a human knowledgeable in chemical structures 
is highly recommended.

Source code and pre-compiled binaries (compiled with MinGW suite for
Windows platform) are available from:
http://cactus.nci.nih.gov/osra
http://osra.sf.net 

Plugins:
Starting with version 1.2.0 plugins for BKChem, MolSketch, Symyx Draw, and
Scitegic PipelinePilot are now included with Windows zip archive.
Plugins allow for integration of OSRA functionality with chemical structure
editors and other chemoinformatics software.
=============================================================================
Dependencies:

OSRA needs the following Open Source libraries installed:

- GraphicsMagick, image manipulation library (faster ImageMagick clone)
version 1.3.7 or later
if installing from RPM make sure you have the following packages:
	GraphicsMagick
	GraphicsMagick-devel
	GraphicsMagick-c++-devel
	GraphicsMagick-c++ 
http://www.graphicsmagick.org/

- POTRACE, vector tracing library, version 1.7 or later,
http://potrace.sourceforge.net/

- GOCR/JOCR, optical character recognition library, version 0.43 or later 
(version 0.45 recommended, do not use 0.46! See special instructions for 0.47
compilation below)
http://jocr.sourceforge.net/

- OCRAD, optical character recognition program, version 0.19. Unpack the
downloaded source code only. Do not compile or install OCRAD - OSRA will 
automatically patch it and compile the object files it needs.
http://www.gnu.org/software/ocrad/ocrad.html

- TCLAP, Templatized C++ Command Line Parser Library, version 1.1.0,
http://tclap.sourceforge.net/

- OpenBabel, open source chemistry toolbox, version 2.2.0 or later; 
if installing from RPM make sure you have the following packages:
	 openbabel 
	 openbabel-devel
http://openbabel.sourceforge.net/wiki/Main_Page

=============================================================================
Other acknowledgements:

OSRA also makes use of the following software (you do not need to
install it separately, it's included in the distribution):

- ThinImage, C code from the article
  "Efficient Binary Image Thinning using Neighborhood Maps"
  by Joseph M. Cychosz, 3ksnn64@ecn.purdue.edu
  in "Graphics Gems IV", Academic Press, 1994
http://www.acm.org/pubs/tog/GraphicsGems/gemsiv/thin_image.c

- GREYCstoration, Anisotropic smoothing plugin,
http://www.greyc.ensicaen.fr/~dtschump/greycstoration/

- CImg, The C++ Template Image Processing Library,
http://cimg.sourceforge.net

- MCDL utility from Sergei Trepalin and Andrei Gakh for 2D coordinate generation

- Unpaper, is a post-processing tool for scanned sheets of paper, especially for book pages that have been scanned from previously created photocopies. The main purpose is to make scanned book pages better readable on screen after conversion to PDF. Additionally, unpaper might be useful to enhance the quality of scanned pages before performing optical character recognition (OCR). 
http://unpaper.berlios.de/

=============================================================================
Compilation:

On Linux/Unix systems:
Compile and/or install all the necessary dependencies. Note - starting with ocrad-0.19
OCRAD compilation generates libocrad.a library used by OSRA. You need to run
./configure;make to compile OCRAD.
Special note for GOCR - do not use version 0.46; I recommend using 0.45
for the moment - it seems to give the best results. If using version 0.47 or
later compile the library libPgm2asc.a the following way:
cd gocr-0.47
 ./configure CPPFLAGS=-fPIC LDFLAGS=-fPIC
make libs

Unpack OSRA package.
Edit the included Makefile to make sure you have the correct locations
for potrace, gocr, ocrad, openbabel (or rdkit), and tclap.
Check that Magick++-config  location (it's a script that
comes from ImageMagick installation) is in your PATH. You might have to set 
LD_LIBRARY_PATH to /usr/local/bin.
Set ARCH variable to one of the following: unix - for linux,unix,osx;
win32 - for building on Windows MinGW environment.
Running make should then generate the executable - osra.

Mac OS X (courtesy of Emilio Esposito):
I recommend using GraphicsMagick as  it is much faster than ImageMagick.
Note - the older version of imagemagick, available from fink seems 
to compile fine but does not work. The version from macports works fine.
Download OSRA, POTRACE, GOCR OCRAD, TCLAP, and OpenBabel. Unzipped and
untarred the downloaded files into ~/software.
Compile POTRACE and GOCR as follows: 
./configure;make
Compile and install TCLAP and OpenBabel as follows: 
./configure;make;sudo make install
GraphicsMagick:
./configure --disable-shared --with-x=no --disable-openmp --without-threads --prefix=/Users/igor/build LDFLAGS=-L/Users/igor/build/lib CPPFLAGS=-I/Users/igor/build/include
make
make install

Then, modify the OSRA Makefile:
ARCH=osx-static
STATIC_LIB_DIR=/Users/igor/build/lib

Running make should then generate the executable - osra.

Compiling OSRA on Windows systems:
It is possible to compile OSRA using either Cygwin or MinGW environment,
however it appears that Cygwin-compiled executable runs about two orders of
magnitude slower than a Linux version running on an equivalent class CPU.
Therefore it is strongly recommended NOT to use Cygwin to compile OSRA.

The instructions below are for MinGW environment.
Before you compile GraphicsMagick it is necessary to install the pre-requisite
libraries - zlib, bzip2, jasper (I was able to compile only version 1.701.0
and not the later 1.900.1), jbigkit, jpeg-6b, lcms, libpng, tiff.
You can read "ADD-ON LIBRARIES & PROGRAMS" for GraphicsMagick which
seems to apply to ImageMagick as well: http://www.graphicsmagick.org/www/README.html

Whenever "configure" script was available I used the following options:
./configure --disable-shared LDFLAGS=-L/usr/local/lib CPPFLAGS=-I/usr/local/include

To configure GraphicsMagick I used the following options:
./configure --disable-shared --without-threads --disable-openmp LDFLAGS=-L/usr/local/lib/ CPPFLAGS=-I/usr/local/include/

Compile and install GraphicsMagick, GOCR, Potrace, TCLAP.

Compiling OpenBabel version 2.2.2:
Run configure with the following options:
./configure --disable-dynamic-modules CXX=g++-sjlj
make
make install

Compilation breaks, but libopenbabel-3.dll gets compiled and installed,
which is what we need.

Compiling osra:
Edit the Makefile to set ARCH variable to win32.
Running make should (hopefully) generate osra.exe. You're done!
Make sure you take a look at osra.bat to see that the environment is set
correctly. You will still need delegates.xml from ImageMagick installation
and a separately installed Ghostscript if you'd like to process PDF and
PostScript files.

=============================================================================
Usage:

OSRA can process the following types of images:

- Computer-generated 2D structures, such as found on the PubChem website,
http://pubchem.ncbi.nlm.nih.gov/, black-and-white and color (use a
resolution of 72 dpi),

- Black-and-white PDF and PostScript files, including multi-page ones. Please
note that you need Ghostcript installed for ImageMagick to be able to
parse these kinds of files. 

- Scanned images - black-and-white, a resolution of 300 dpi is recommended,
though 150 dpi can also produce fair results. Please make sure the
scanned image is of reasonable quality - an input that's too noisy will 
only generate garbage output.

Some common abbreviations, hetero atoms, fused and merged atomic
labels, hash and wedge bonds, and bridge bonds are currently
recognized. Formal charges, isotopes and some element
symbols, i.e. iodine ("I" -- looks too much like a straight line = single
bond), are not.


Command-line options: 
./osra --help 
will give you a list of available options with short descriptions.

Most common use: ./osra [-r <resolution>] <filename>

Resolution in dpi, default is "0" for automatic resolution
determination.
Filename is the name of your image file (or PS/PDF document).

Other options: 
-t, --threshold: Gray level threshold, default is 0.2
                 for black-and-white images, 

-n, --negate:    Inverts colors (for white on black images),

-o, --output:    Sets a prefix for writing recognized images to files - i.e.
                 "-o tmp" will create files tmp0.png, tmp1.png... for
                 each of the structures,

-s, --size:      Resize images on output - can be useful for running OSRA
                 as a backend for a webservice. Example: "-s 300x400".

-g,  --guess:    Prints out resolution guess when you chose to have automatic
     		 resolution estimate

-p,  --print:    Prints out the value of confidence function estimate


-f,  --format:  Output format (either smi for SMILES or sdf for SD file format)

-d,  --debug:    Print out debug information on spelling corrections

-a <configfile>,  --superatom <configfile>:  Superatom label map to SMILES (superatom.txt by default)

-l <configfile>,  --spelling <configfile>:   Spelling correction dictionary (spelling.txt by default)

-u <roundss>,  --unpaper <rounds>
     Pre-process image with unpaper algorithm, rounds (default: do not pre-process)

-w <filename>, --write <filename> Write output to a file instead of stdout

-b, --bond: Print out average bond length in pixels

=============================================================================
Windows users:
You can download and use either the self-installing exe file or
the zip archive with the pre-compiled binary.
The archive includes:
	osra.bat - Windows batch file, sets the environment and calls
	           osra.exe, 
	osra.exe - main executable. It is recommended that you use osra.bat
		   and not osra.exe directly,
	README.txt   - this file,
        delegates.xml - a configuration file for ImageMagick library,
        Read Image Document (OSRA).xml - a simple Scitegic Pipeline Pilot
	                                 component to read in an image
					 document using OSRA.
To use: 
Make sure you have installed Ghostscript (32-bit executable) and edit if
necessary osra.bat to have the correct PATH (by default it points to version
8.61). Run osra.bat with the same options as described above for the Linux
version. You can also run OSRA through Pipiline Pilot by using the included
protocol. It runs osra.bat on the client by default but it's trivial to
modify the component for "Run on Server" option.
============================================================================
LICENSE:

This program is free software; the part of the software that was written 
at the National Cancer Institute is in the public domain.  This does not
preclude, however, that components such as specific libraries used in the
software may be covered by specific licenses, including but not limited
to the GNU General Public License as published by the Free Software Foundation; 
either version 2 of the License, or (at your option) any later version; 
which may impose specific terms for redistribution or modification.

This program is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307,
USA. See also http://www.gnu.org/.

See the file COPYING for details.
====================================================================
