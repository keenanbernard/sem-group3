# USE CASE: Produce Country Reports

## CHARACTERISTIC INFORMATION

### Goal in Context

As an Organization Worker at the World Health Organization I want to be able to produce the following Country Reports supporting the senior management in allocating WHO resources:
1. All the countries in the world organised by largest population to smallest.
2. All the countries in a continent organised by largest population to smallest.
3. All the countries in a region organised by largest population to smallest.
4. The top N populated countries in the world where N is provided by the user.
5. The top N populated countries in a continent where N is provided by the user.
6. The top N populated countries in a region where N is provided by the user.

### Scope

Organization.

### Level

Primary task.

### Preconditions

Database contains current world data, inclusive of the required countries.

### Success End Condition

A report is available for the organization.

### Failed End Condition

No report or an inaccurate report is produced.

### Primary Actor

Organization Worker.

### Trigger

A request for this specific report was issued by the organization.

## MAIN SUCCESS SCENARIO

1. Organization request countries population organised by largest population to the smallest.
2. Organization worker triggers the report generation.
3. Organization worker provides report to Organization.

## EXTENSIONS

None.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
