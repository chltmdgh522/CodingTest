N = gets.chomp.to_i

stack = []
N.times do
  message = gets.chomp.split.map(&:to_s)

  case message[0]
  when "push"
    stack.push(message[1].to_i)
 
  when "pop"
    num = stack.pop
    if num == nil
      puts -1
    else
      puts num
    end
  when "size"
    puts stack.size
  when "empty"
    if stack.empty?
      puts 1
    else
      puts 0
    end
  when "top"
    if stack.size == 0
      puts -1
    else
      puts stack.last
    end
  end
end