# USE CASE: Produce Capital Cities Reports

## CHARACTERISTIC INFORMATION

### Goal in Context

As an Organization Worker at the World Health Organization I want to be able to produce the following Capital Cities Reports supporting the senior management in allocating WHO resources:
1. All the cities in the world organised by largest population to smallest.
2. All the cities in a continent organised by largest population to smallest.
3. All the cities in a region organised by largest population to smallest.
4. All the cities in a country organised by largest population to smallest.
5. All the cities in a district organised by largest population to smallest.
6. The top N populated cities in the world where N is provided by the user.
7. The top N populated cities in a continent where N is provided by the user.
8. The top N populated cities in a region where N is provided by the user.
9. The top N populated cities in a country where N is provided by the user.
10. The top N populated cities in a district where N is provided by the user.

### Scope

Organization.

### Level

Primary task.

### Preconditions

Database contains current world data, inclusive of the required cities.

### Success End Condition

A report is available for the organization.

### Failed End Condition

No report or an inaccurate report will be produced.

### Primary Actor

Organization Worker.

### Trigger

A request for this specific report was issued by the organization.

## MAIN SUCCESS SCENARIO

1. Organization request Cities population organised by either the top inputted number or largest population to the smallest.
2. Organization worker triggers the report generation.
3. Organization worker provides report to Organization.

## EXTENSIONS

None.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
