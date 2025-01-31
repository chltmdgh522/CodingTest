N = gets.chomp.to_i

queue = []
for i in 1..N
  queue.push(i)
end

cnt=0

while queue.size > 1
  if cnt % 2 == 0
    queue.shift
  elsif cnt % 2== 1
    queue.push(queue.shift)
  end
  cnt+=1
end

puts queue.join

