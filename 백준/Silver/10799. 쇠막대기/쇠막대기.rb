message=gets.chomp.to_s

count=0
stack=[]
answer=0
message.each_char do |char|
  if message[count]=="("
    stack.push("(")
  else
    stack.pop
    if message[count-1] == "("
      answer+=stack.size
    else
      answer+=1
    end
  end
  count+=1
end

puts answer