# [<img src="ao-logo.png" alt="AO Logo" width="35" height="40">](https://github.com/aoindustries) [AO Fluent HTML](https://github.com/aoindustries/ao-fluent-html)
<p>
	<a href="https://aoindustries.com/life-cycle#project-alpha">
		<img src="https://aoindustries.com/ao-badges/project-alpha.svg" alt="project: alpha" />
	</a>
	<a href="https://aoindustries.com/life-cycle#management-preview">
		<img src="https://aoindustries.com/ao-badges/management-preview.svg" alt="management: preview" />
	</a>
	<a href="https://aoindustries.com/life-cycle#packaging-developmental">
		<img src="https://aoindustries.com/ao-badges/packaging-developmental.svg" alt="packaging: developmental" />
	</a>
	<br />
	<a href="https://docs.oracle.com/javase/8/docs/api/">
		<img src="https://aoindustries.com/ao-badges/java-8.svg" alt="java: &gt;= 8" />
	</a>
	<a href="http://semver.org/spec/v2.0.0.html">
		<img src="https://aoindustries.com/ao-badges/semver-2.0.0.svg" alt="semantic versioning: 2.0.0" />
	</a>
	<a href="https://www.gnu.org/licenses/lgpl-3.0">
		<img src="https://aoindustries.com/ao-badges/license-lgpl-3.0.svg" alt="license: LGPL v3" />
	</a>
</p>

Fluent Java DSL for high-performance HTML generation.

## Project Links
* [Project Home](https://aoindustries.com/ao-fluent-html/)
* [Changelog](https://aoindustries.com/ao-fluent-html/changelog)
* [API Docs](https://aoindustries.com/ao-fluent-html/apidocs/)
* [Maven Central Repository](https://search.maven.org/#search%7Cgav%7C1%7Cg:%22com.aoindustries%22%20AND%20a:%22ao-fluent-html%22)
* [GitHub](https://github.com/aoindustries/ao-fluent-html)

## Modules
* [AO Fluent HTML Servlet](https://github.com/aoindustries/ao-fluent-html-servlet)

## Motivation
We have a lot of legacy servlet-based code that directly writes HTML as strings.
This technique is both tedious and error-prone.  We've been refactoring code to
support HTML 5, and now is a good time to clean this up without radically
rewriting everything.

## Features
* Supports both HTML 4.01, XHTML 1.0, HTML 5 and XHTML 5.
* Supports both SGML and XML serializations.
* Fluent API.
* Integrates with AO in-context translation tools.
* Streaming implementation, including attributes.
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
time, however, we still required support for XHTML 1.0, HTML 4, and XHTML 5.
This, along with interoperability with our other projects, is steering us toward
our own implementation.

## Contact Us
For questions or support, please [contact us](https://aoindustries.com/contact):

Email: [support@aoindustries.com](mailto:support@aoindustries.com)  
Phone: [1-800-519-9541](tel:1-800-519-9541)  
Phone: [+1-251-607-9556](tel:+1-251-607-9556)  
Web: https://aoindustries.com/contact