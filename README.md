# sales-problem
An example application for a classic sales problem

A java console application that allows a seller to record product sales and make adjustments to recorded sales.

Basic commands:

Add a sale:

Sale [product-name] [cost] *[quantity]
* optional parameter, defaults to 1

examples:

Sale apple 1
Sale apple 1 4

Add an adjustment:

Adjust [operation] [product-name] [amount]

example:
Adjust add apple 1

Bulk load messages:
Allows a text file with messages separated by line to be loaded from a single command. 

Load [file-name]

examples:
Load test.txt