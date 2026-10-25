use std::fs::File;
use std::io::{BufRead, BufReader};

fn read_input(filename: String) -> Vec<Vec<i32>> {
    let file = File::open(filename).expect("no such file");
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

fn is_safe_level(level: &Vec<i32>) -> bool {
    if level.len() < 2 {
        return true;
    }
    
    let is_increasing = level[0] < level[1];
    
    for i in 0..level.len() - 1 {
        let diff = level[i + 1] - level[i];
        
        // Check if the sequence is consistent (all increasing or all decreasing)
        if is_increasing && diff <= 0 {
            return false;
        }
        if !is_increasing && diff >= 0 {
            return false;
        }
        
        // Check if difference is within allowed range (1-3)
        if diff.abs() < 1 || diff.abs() > 3 {
            return false;
        }
    }
    
    true
}

fn level_is_safe(level: &Vec<i32>) -> bool {
    // First check if the level is already safe
    if is_safe_level(level) {
        return true;
    }
    
    // If not safe, try removing each element one by one
    for i in 0..level.len() {
        let mut test_level = level.clone();
        test_level.remove(i);
        
        if is_safe_level(&test_level) {
            return true;
        }
    }
    
    false
}

fn safe_count(report: &Vec<Vec<i32>>) -> usize {
    report.iter().filter(|level| level_is_safe(&level))
        .count()
}

pub fn execute() {
    let report = read_input("../input.txt".to_string());

    println!("[Task 2] report: {:?}", report);
    for level in &report {
        println!("{:?} -> safe? : {}", level, level_is_safe(&level));
    }

    println!("[Task 2] safe count: {}", safe_count(&report));
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_level_is_safe() {
        assert_eq!(level_is_safe(&vec![1, 2, 3, 4, 5]), true);
        assert_eq!(level_is_safe(&vec![5, 4, 3, 2, 1]), true);
        assert_eq!(level_is_safe(&vec![1, 3, 2, 4, 5]), true);
        assert_eq!(level_is_safe(&vec![1, 2, 3, 4, 10]), true);
        assert_eq!(level_is_safe(&vec![10, 5, 3, 2, 1]), true);
        assert_eq!(level_is_safe(&vec![1, 5, 3, 2, 1]), true);
        assert_eq!(level_is_safe(&vec![1, 3, 2, 4, 1]), false);
        assert_eq!(level_is_safe(&vec![1, 3, 4, 8, 5]), true);
        let report = vec![1, 2, 3, 4, 10];
        println!("Test Report: {:?}", report);
        assert_eq!(level_is_safe(&report), true);
    }

    #[test]
    fn test_count_safe_levels() {
        let read_input = read_input("../example.txt".to_string());
        assert_eq!(safe_count(&read_input), 4);
    }
}
