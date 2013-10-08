MatlabHeaderHtml
===============

MatlabHeaderHtml parses the header of your MATLAB .m files and convert it into minimalist browsable html files.

It is a command-line tool written in Java.

Format
--------------

For MatlabHeaderHtml to work, .m files header (i.e. first few lines) most be formatted the following way :

	% YOUR_FUNCTION One-liner description
	%
	% Usage
	%	[out1, out2] = YOUR_FUNCTION(in1, in2)
	%
	% Input
	%	in1 (type): brief description of first input
	%	in2 (type): brief description of second input
	%
	% Output
	%	out1 (type): brief description of first output
	%	out2 (tyoe): brief description of second output
	%
	% Description
	%	Detail description of your matlab function goes here
	%
	% See also
	%	ANOTHER_FUNCTION_RELATED_TO_YOUR_FUNCTION, YET_ANOTHER_FUNCTION_RELATED
	%
	function [out1, out2] = your_function(in1, in2)

Usage
--------------

