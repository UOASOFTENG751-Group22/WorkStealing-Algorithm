# WorkStealing-Benchmarking

Softeng project 3 - Work Stealing benchmarking part.

This project aims at benchmarking the work stealing mechanism of original ParaTask project and our own implementation of work stealing algorithm which is integrated in ParaTask runtime. 

# Benchmark implementation

1)	computation-intensive(controllable) program:  
    src:uoa.se751.group22.utils.Work  
2)  benchmark program:  
    src:uoa.se751.group22.benchmark.Benchmark.ptjava  
    This program will initialize the problem size and schedule type during the execution time.
3)  scripts:  
    run.py : used to generate benchmarking dataset  
    >>>>>>>>>the program will generate the result folder to store data_set.csv file for us to analyse  
    plotting.py : used to plot benchmarking graphs  
    >>>>>>>>>the program will generate the plot folder to store analysis.svg file which shows:  
    >>>>>>>>>>>>1) execution time changes with increasing problem size  
    >>>>>>>>>>>>2) speedup with increasing problem size
