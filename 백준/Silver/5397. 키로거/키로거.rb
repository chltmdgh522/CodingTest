def find_password(logs)
  left_stack = []
  right_stack = []

  logs.each_char do |char|
    case char
    when '-'
      left_stack.pop unless left_stack.empty?
    when '<'
      right_stack.unshift(left_stack.pop) unless left_stack.empty?
    when '>'
      left_stack.push(right_stack.shift) unless right_stack.empty?
    else
      left_stack.push(char)
    end
  end

  puts (left_stack + right_stack).join
end

t = gets.to_i
t.times do
  logs = gets.chomp
  find_password(logs)
end
