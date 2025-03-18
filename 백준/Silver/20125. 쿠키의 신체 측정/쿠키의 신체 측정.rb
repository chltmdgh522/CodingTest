N = gets.chomp.to_i

answer = Array.new(5, 0)
heart = 0
answerHeart = Array.new(2, 0)
heartFlag = false
headFlag = false
coreFlag = false

core = 0

for i in 0...N
  input = gets.chomp.to_s

  # 허리 구하기 및 다리 구하기
  if coreFlag
    # 허리 구하기
    if input.index('*') == answerHeart[1] - 1
      core += 1
    else # 다리 구하기
      answer[2] = core # 허리 입력
      # 다리 2개 다 있을때
      if input.count('*') == 2
        answer[4] += 1
        answer[3] += 1
        # 다리 1 개 있을떄
      elsif input.count('*') == 1
        if input.index('*') > heart
          answer[4] += 1
        elsif input.index('*') < heart
          answer[3] += 1
        end
        # 다리 없을때 종료
      elsif input.index('*') == nil
        coreFlag = false
        break

      end
    end
  end

  # 왼팔 오른팔 구하기
  if heartFlag
    leftArm = input.index('*')
    rightArm = input.rindex('*')
    answer[0] = heart - leftArm
    answer[1] = rightArm - heart
    heartFlag = false
    coreFlag = true
  end

  # 머리 구하기 따라서 바로 아래 심장 구할 수 있음
  if input.index('*') != nil && !headFlag
    heart = input.index('*')
    answerHeart[0] = i + 2
    answerHeart[1] = heart + 1
    heartFlag = true
    headFlag = true
  end

end

puts answerHeart.join(" ")
puts answer.join(" ")
