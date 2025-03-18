S=gets.chomp.to_s

message=""
space_message=""
word=""
flag=false
space =false
S.each_char.with_index do |char, i|
  # 띄어쓰기 만났을떄 저장
  if char ==" " && !space
    message += word.reverse
    message+=" "
    word=""
    # 마지막 문자
  elsif S.length-1 == i && char != ">"
    word+=char
    message +=word.reverse
    word =""
    # >tag< 이렇게 사이에있을떄
  elsif S[i+1] == "<" && char !=">"

    word+=char
    message+=word.reverse
    word=""
    # < 이거 만났을떄
  elsif char == "<" || space
    space= true

    if char != "<" && char != ">"
      word+=char

    elsif char == ">"
      message += "<" + word + ">"
      space =false
      word = ""
    end
  else
    word+=char
  end
end

puts message