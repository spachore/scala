/*
 *    Copyright 2011-2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.scala.mapping

import org.mybatis.scala.session.Session

import scala.reflect.ClassTag

/** A mapped SQL INSERT statement.
  * Basically this defines a function: (Param => Int)
  * @tparam Param Input parameter type of the apply method.
  * @version \$Revision$
  */
abstract class Insert[Param : ClassTag](implicit val ct: ClassTag[Param])
  extends Statement
     with SQLFunction1[Param,Int] {

  /** Key Generator used to retrieve database generated keys.
    * Defaults to null
    */
  var keyGenerator : KeyGenerator = null //JdbcGeneratedKey(null, "id")

  def parameterTypeClass = ct.runtimeClass

  /** Exceutes the SQL INSERT Statement
    * @param param Input paramenter of the statement
    * @param s Implicit Session
    * @return number of affected rows
    */
  def apply(param : Param)(implicit s : Session) : Int =
    execute { s.insert(fqi.id, param) }

}
