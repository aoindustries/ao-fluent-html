# [<img src="ao-logo.png" alt="AO Logo" width="35" height="40">](https://github.com/aoindustries) [AO OSS](https://github.com/aoindustries/ao-oss) / [Fluent HTML](https://github.com/aoindustries/ao-fluent-html)

[![project: alpha](https://oss.aoapps.com/ao-badges/project-alpha.svg)](https://aoindustries.com/life-cycle#project-alpha)
[![management: preview](https://oss.aoapps.com/ao-badges/management-preview.svg)](https://aoindustries.com/life-cycle#management-preview)
[![packaging: developmental](https://oss.aoapps.com/ao-badges/packaging-developmental.svg)](https://aoindustries.com/life-cycle#packaging-developmental)  
[![java: &gt;= 11](https://oss.aoapps.com/ao-badges/java-11.svg)](https://docs.oracle.com/en/java/javase/11/docs/api/)
[![semantic versioning: 2.0.0](https://oss.aoapps.com/ao-badges/semver-2.0.0.svg)](http://semver.org/spec/v2.0.0.html)
[![license: LGPL v3](https://oss.aoapps.com/ao-badges/license-lgpl-3.0.svg)](https://www.gnu.org/licenses/lgpl-3.0)

Fluent Java DSL for high-performance HTML generation.

## Project Links
* [Project Home](https://oss.aoapps.com/fluent-html/)
* [Changelog](https://oss.aoapps.com/fluent-html/changelog)
* [API Docs](https://oss.aoapps.com/fluent-html/apidocs/)
* [Maven Central Repository](https://search.maven.org/artifact/com.aoapps/ao-fluent-html)
* [GitHub](https://github.com/aoindustries/ao-fluent-html)

## Modules
* [AO Fluent HTML Any](https://github.com/aoindustries/ao-fluent-html-any)
* [AO Fluent HTML Servlet](https://github.com/aoindustries/ao-fluent-html-servlet)
* [AO Fluent HTML Util](https://github.com/aoindustries/ao-fluent-html-util)

## Motivation
We have a lot of legacy servlet-based code that directly writes HTML as strings.
This technique is both tedious and error-prone.  We've been refactoring code to
support HTML 5, and now is a good time to clean this up without radically
rewriting everything.

## Features
* Supports HTML 4.01, XHTML 1.0, HTML 5 and XHTML 5.
* Supports both SGML and XML serializations.
* Fluent API.
* Also supports lambda element content, which makes it easier to code and debug than a strict fluent-only approach.
* Context-aware compiler and code assistance through extensive use of the interface hierachy along with bounded self-referential generics.
* Everything is [encoded and safe by default](https://github.com/aoindustries/ao-encoding), with <code>unsafe(…)</code> methods allowing raw output.
* Optional automatic newline and tab indentation.
* Optimized streaming implementation, including attributes.
* Integrates with AO in-context translation tools.
* Stupid fast (TODO: benchmark).
* Separate module for use in a Servlet environment.

## Limitations
Not all tags and attributes are implemented.  We are implementing
as we go.  A full implementation of all tags and attributes would
probably be best achieved with code generation.  This project will
remain below version <code>1.0.0</code> until it has a reasonably
complete implementation.

## Evaluated Alternatives
### [HtmlFlow](https://github.com/xmlet/HtmlFlow)
[HtmlFlow](https://github.com/xmlet/HtmlFlow) is a Java DSL to write typesafe
HTML documents in a fluent style.  It is very close to what we want.  We
may look into integrating our unique needs with their implementation.  At this
time, however, we still require support for XHTML 1.0, HTML 4, and XHTML 5.
This, along with interoperability with our other projects, is steering us toward
our own implementation.

## Contact Us
For questions or support, please [contact us](https://aoindustries.com/contact):

Email: [support@aoindustries.com](mailto:support@aoindustries.com)  
Phone: [1-800-519-9541](tel:1-800-519-9541)  
Phone: [+1-251-607-9556](tel:+1-251-607-9556)  
Web: https://aoindustries.com/contact
