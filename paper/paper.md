---
title: 'Coordinate: An Android application to organize samples into grids'
tags:
  - data management
  - sample tracking
authors:
  - name: Trevor W. Rife
    orcid: 0000-0001-6797-1221
    affiliation: 1
  - name: Chaney Courtney
    orcid: 0000-0001-7602-0487
    affiliation: 1
  - name: Jesse A. Poland
    orcid: 0000-0003-3825-8480
    affiliation: 2
affiliations:
 - name: Department of Plant and Environmental Sciences, Clemson University
   index: 1
 - name: Biological and Environmental Science and Engineering Division, King Abdullah University of Science and Technology
   index: 2
date: 1 June 2023
bibliography: paper.bib
---

# Summary

Data management is one of the most important aspects of modern science. Biological sciences rely on organizing samples into grids and tracking each individual sample. Collecting and organizing these samples is especially important for processes like high throughput genotyping where thousands of samples are collected and must be individually tracked throughout the genotyping process. Coordinate, an open-source Android application, helps with grid-based data collection by creating a visual grid layout and allows users to assign samples to specific cells in the grid. Collected data can be exported and used as inputs for down-stream analyses. Coordinate is highly customizable, allowing templates to be specifically defined for different end uses.


# Statement of need

* Substantial portions of data are collected in grids
* Ensuring data collected in these grids can be used for later analysis requires detailed documentation
* Errors in sample collection/organization/data management actively harm progress
* Data collected at scale requires optimized tools and workflows
* Defined workflows reduce the odds of mistakes and increase the rate at which data can be collected and utilized
* Coordinate provides an adaptable, scalable framework to organize samples into grids
* Images of Coordinate in action


# Features

Coordinate is organized around a hierarchical system of grids, templates, cells, and projects. Grids inherit specific properties from user-defined templates and are comprised of rows and columns of individual cells where users input data. Grids can be organized into projects to facilitate collective management.

Coordinate allows templates to be defined to match specific user needs. The number of rows and columns can be adjusted to create differently shaped grids. Different metadata fields can be defined that will be collected for each grid. Default metadata fields include the grid identifier, person, and date. User-created metadata fields can be further customized to include a default value. Templates can also define specific or random cells to be excluded from each grid. This is helpful for genotyping plates where vendors may require specific cells to be empty or for quality control where a random well can be used to differentiate plates that have been collected within a single project.

, and different naming naming rows and columns

* User definable templates
** # rows/cols, custom metadata fields with prefilled information, excluding specific cells (random or selected), naming, 

* User definable grid
** Add to project, 

* Barcode input
* Options: direction modification, notification sounds, unique values required, project export, scaling


# Additional resources

Documentation for Coordinate is available at https://docs.coordinate.phenoapps.org/ .

# Acknowledgements

The development of Coordinate was supported by the National Science Foundation under Grant No. (1543958). This app is made possible by the support of the American People provided to the Feed the Future Innovation Lab for Crop Improvement through the United States Agency for International Development (USAID) under Cooperative Agreement No. 7200AA-19LE-00005. The contents are the sole responsibility of the authors and do not necessarily reflect the views of USAID or the United States Government.

# References
