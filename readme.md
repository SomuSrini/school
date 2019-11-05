# Code Review Exercise

## Intro

The following assignment will test your ability to perform, with an eye towards design and style flaws in code.

## Background

The following application models a simple school, containing students and subjects. 
Students take multiple subjects, and subjects are taken by multiple students.

Your task is to review a new feature produced by a junior developer that adds the concept of grades.
A student has both an original grade for a class, as well as an adjusted grade. 
An administrator may decide to adjust grades for a class as a way to normalize the distribution of marks. 

## Instructions
1. [Apply the patch](https://git-scm.com/docs/git-apply) `0001-Add-grading.patch` found in the root directory.
2. Add inline comments to the code that was added via the patch.

- Please be very clear and concise with your comments. 
- You can assume the code prior to the patch is working as intended.
- You may choose to comment on code outside the patch if you see it as being problematic, but emphasis will be placed on reviewing the code added in the patch.

Please include any additional notes in `notes.md` including assumptions made, 
design decisions, additional libraries added or anything that you did that was beyond the asks.

_Hint: There are currently 30 known issues dispersed throughout various files in the project.
These range in categories from, but not limited to, RESTful design, performance bottlenecks, ORMs, input validation, test methodologies, and miscellaneous defects.
You will be evaluated on how many of these known issues you are able to correctly identify, any additional areas you are able to correctly identify, and the clarity of your comments._

## Submission
Please submit a single [patch file](https://git-scm.com/docs/git-format-patch) of your changes.

#### Documentation
For more information about Spring Boot and the related dependencies, please consult the `HELP.md` file.