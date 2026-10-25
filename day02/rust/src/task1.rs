use std::fs::File;
use std::io::{BufRead, BufReader};

fn read_input() -> Vec<Vec<i32>> {

    let file = File::open("../input.txt").expect("no such file");
    let reader = BufReader::new(file);

    let mut report: Vec<Vec<i32>> = Vec::new();
    for lines in reader.lines() {

        let lines = lines.expect("");
        let collect: Vec<&str> = lines.split_whitespace().collect();

        let level: Vec<i32> = collect.iter().filter_map(|v| v.parse::<i32>().ok())
            .collect();

        report.push(level);
    }

    report

}

fn level_is_safe(level: &[i32]) -> bool {
    let mut index = 0;
    let max = level.len();
    let mut safe = true;

    // Check if everything is decreasing 
    while index+1 < max && safe {
        safe = level[index] < level[index+1] && (level[index] - level[index+1]).abs() <=3;
        index += 1;
    }

    // Check if everything is increasing
    if !safe {
        index = 0;
        safe = true;
        while index+1 < max && safe {
            safe = level[index] > level[index+1] && (level[index] - level[index+1]).abs() <=3;
            index += 1;
        }
    }
    safe
}

fn safe_count(report: &Vec<Vec<i32>>) -> usize {
    report.iter().filter(|level| level_is_safe(&level))
        .count()
}

pub fn execute() {

    let report = read_input();

    println!("[Task 1] report: {:?}", report);
    for level in &report {
        println!("{:?} -> save? : {}", level, level_is_safe(&level));
    }

    println!("[Task 1] safe count: {}", safe_count(&report));
}
