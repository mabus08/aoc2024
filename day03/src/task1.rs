use std::fs::File;
use std::io::prelude::*;
use std::path::Path;
use regex::Regex;

fn read_file(path: &str) -> String {
    // Create a path to the desired file
    let path = Path::new(path);
    let display = path.display();

    // Open the path in read-only mode, returns `io::Result<File>`
    let mut file = match File::open(&path) {
        Err(why) => panic!("couldn't open {}: {}", display, why),
        Ok(file) => file,
    };

    // Read the file contents into a string, returns `io::Result<usize>`
    let mut s = String::new();
    match file.read_to_string(&mut s) {
        Err(why) => panic!("couldn't read {}: {}", display, why),
        Ok(_) => print!("{} contains:\n{}", display, s),
    }

    // s goes out of scope here
    s
}


fn accumulate_from_string(from_string: &str) -> i32 {
    // use capture groups to adress identified numbers 
    let regex = Regex::new(r"mul\(([0-9]{1,3}),([0-9]{1,3})\)").unwrap();

    let mut acc = 0;
    for line in regex.find_iter(from_string) {
        if let Some(captures) = regex.captures(line.as_str()) {
            let first = &captures[1].parse::<i32>().unwrap();
            let second = &captures[2].parse::<i32>().unwrap();
            acc += first * second;
        }
    }
    acc
}

pub fn execute() {
    let read_file = read_file("input.txt");
    let acc = accumulate_from_string(&read_file);
    println!("Summe: {}", acc);
}

#[cfg(test)]
mod test {
    use super::*;

    #[test]
    fn test_example() {
        let read_file = read_file("example.txt");
        assert_eq!(accumulate_from_string(&read_file), 161);
    }
}
