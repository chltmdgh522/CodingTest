N=gets.chomp.to_i

queue=[]

N.times do
  message=gets.chomp.split.map(&:to_s)

  case message[0]
  when "push"
    queue.push(message[1].to_i)
  when "pop"
    num = queue.shift
    if num == nil
      puts -1
    else
      puts num
    end
  when "size"

    puts queue.size
  when "empty"
    if queue.empty?
      puts 1
    else
      puts 0
    end
  when "front"
    if !queue.empty?
      puts queue.first
    else
      puts -1
    end
  when "back"
    if !queue.empty?
      puts queue.last
    else
      puts -1
    end
  end
end