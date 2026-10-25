// task02.rs
use std::fs::File;
use std::io::{self, BufRead, BufReader};

struct Context {
    arr1: Vec<i32>,
    arr2: Vec<i32>,
}

fn read_file(path: &str) -> io::Result<File> {
    File::open(path)
}

fn read_context() -> Option<Context> {
    let mut context = Context {
        arr1: Vec::new(),
        arr2: Vec::new(),
    };
    let file_input = read_file("/home/mabus/projects/aoc2024/day01/my_input.txt")
        .expect("Should have been able to read the file");
    let reader = BufReader::new(file_input);
    for line in reader.lines() {
        let line = line.expect("");
        let parts: Vec<&str> = line.split_whitespace().collect();
        if parts.len() >= 2 {
            let val1: i32 = parts[0].parse().expect("");
            let val2: i32 = parts[1].parse().expect("");

            context.arr1.push(val1);
            context.arr2.push(val2);
        }
    }
    Some(context)
}

fn amount_in(number: i32, right_vec: &Vec<i32>) -> i32 {
    let mut result = 0;
    for c in right_vec {
        if *c == number {
            result += 1;
        }
    }
    result
}

fn sum(from_vec: &Vec<i32>) -> i32 {
    let mut sum = 0;
    for i in from_vec {
        sum += *i;
    }
    sum
}

pub fn execute() {
    let context = read_context().expect("no context found!");

    let mut sum_vec: Vec<i32> = Vec::new();

    let left_vec = context.arr1;
    let right_vec = context.arr2;

    for i in left_vec {
        sum_vec.push(amount_in(i, &right_vec) * i);
    }

    // println!("[Task 2] sum_vec: {:?}", sum_vec);
    println!("[Task 2] Sum: {:?}", sum(&sum_vec));
}
