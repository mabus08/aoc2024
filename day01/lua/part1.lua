FILENAME = "../my_input.txt";

-- read a file 
function readFile()
    local file = io.open(FILENAME, "r")  -- Open the file in read mode
    if not file then
        error("Could not open file: " .. FILENAME)
    end

    local data = {}  -- Initialize the 2D array
    for line in file:lines() do 
        table.insert(data, line)
    end
    file:close()
    return data;
end

function splitColumns(line)
    local result = {}
    for w in line:gmatch("%S+") do -- match all non-space characters
        table.insert(result, tonumber(w))
    end
    return result;
end

function transpose(Darray)
    local rows = {}
    -- initialize each row with new list
    for col = 1, #Darray[1] do
        rows[col] = {}
    end
    for row = 1, #Darray do
        for col = 1, #Darray[row] do
            rows[col][row] = Darray[row][col]
            table.sort(rows[col])
        end
    end
    return rows;
end

function debugPrint(array)
    print("Debugging output....")
    for row = 1, #array do
        for col = 1, #array[row] do
            io.write(array[row][col] .. " ")
        end
        print()
    end
end

local fileinput = readFile();
local darray = {}
for i,line in ipairs(fileinput) do
    table.insert(darray, splitColumns(line));
end

local output = transpose(darray)
local maxRow = #output
local maxCol = #output[1]

local sum = 0
for r = 1, maxCol do
   for c = 1, maxRow -1 do
       local tsum = math.abs(output[c][r] - output[c+1][r])
       sum = sum + tsum
   end
end
print(math.abs(sum))

