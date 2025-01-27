N = gets.chomp.to_i
list = []
alphabet = Array.new(26, false)

# 입력 받으면서 해결
for start in 0..N - 1
  str = gets.chomp.to_s
  flag = false
  splitFlag = false
  bigStr = false
  # 공백 구분
  str_split = str.split(' ')

  # 첫글자 공략 공백 돌기
  for i in 0..str_split.length - 1
    if !splitFlag
      resultStr = ""
      # 첫글자 있는지 확인
      strSplit = str_split[i]
      if strSplit[0] >= 'A' && strSplit[0] <= 'Z'
        splitOrd = strSplit[0].ord - 65
      else
        splitOrd = strSplit[0].upcase.ord - 65
      end

      if !alphabet[splitOrd]
        strSplit[0] = "[" + strSplit[0] + "]"
        splitFlag = true
        flag = true
        alphabet[splitOrd] = true
        # 합쳐서 넣기
        for k in 0...str_split.length
          if k == i
            resultStr += strSplit
          else
            resultStr += str_split[k]
          end
          resultStr += " "
        end
        list.push(resultStr.strip)
      end

    end
  end

  # 뒷글자 공략
  if !splitFlag
    splitFlag2 = false
    for i in 0..str_split.length - 1
      if !splitFlag2
        resultStr = ""
        # 뒷글자 있는지 확인

        for j in 1...str_split[i].length
          strSplit = str_split[i]
          if strSplit[j] >= 'a' && strSplit[j] <= 'z'
            splitOrd = strSplit[j].upcase.ord - 65
          else
            splitOrd = strSplit[j].ord - 65
          end

          if !alphabet[splitOrd]

            flag = true
            alphabet[splitOrd] = true
            strSplit[j] = "[" + strSplit[j] + "]"
            splitFlag2 = true

            # 합쳐서 넣기
            for k in 0...str_split.length
              if k == i
                resultStr += strSplit
              else
                resultStr += str_split[k]
              end
              resultStr += " "
            end
            list.push(resultStr.strip)
            break
          end
        end
      end
    end
  end

  if !flag
    list.push(str)
  end
end

for k in 0...list.length
  puts list[k]
end