# Gridmize 2
A helper to use grids when working with Selenium WebDriver (Java).

To use it you need to set the WebDriver on GridDriver class, and create an Enum with that implements HeaderInterface. This Enum will have all the headers of your page grid.
The header index is zero based, so keep that in mind when creating that Enum.

An example project is available [here](https://github.com/Jiwari/gridmize-2-example) using a grid from Wikipedia.