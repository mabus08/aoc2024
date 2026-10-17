// main.rs
use std::fs::File;
use std::io::{self, BufRead, BufReader};

fn read_file(path: &str) -> io::Result<Filer> {
    File::open(path)
}

fn main() {
    let file_input = read_file("/home/mabus/projects/aoc2024/day01/my_input.txt")
        .expect("Should have been able to read the file");

    let reader = BufReader::new(file_input);

    let mut left_column = Vec::new();
    let mut right_column = Vec::new();

    for line in reader.lines() {
        let line = line.expect("");
        let parts: Vec<&str> = line.split_whitespace().collect();
        if parts.len() >= 2 {
            let val1: i32 = parts[0].parse().expect("");
            let val2: i32 = parts[1].parse().expect("");

            left_column.push(val1);
            right_column.push(val2);
        }
    }

    left_column.sort_unstable();
    right_column.sort_unstable();



    let mut diff: Vec<i32> = Vec::new();
    let mut sum: i32 = 0;
    for i in 0..left_column.len() {
        diff.push((left_column[i] - right_column[i]).abs());
        sum += (left_column[i] - right_column[i]).abs();
    }
    println!("Left: {:?}", left_column);
    println!("Right: {:?}", right_column);
    println!("Diff: {:?}", diff);
    println!("Sum: {}", sum);
}

fn sort(nums: Vec<i32>) -> Vec<i32> {
    println!("unsorted left: {:?}", nums);
    let mut ret: Vec<i32> = Vec::new();
    
    for i in 0..nums.len() {
        for y in 1..nums.len() {
            if nums[i] > nums[y] {
                ret.push(nums[y]);
            } else {
                ret.push(nums[i]);
            }
        }
    }
    
    return ret;
}
